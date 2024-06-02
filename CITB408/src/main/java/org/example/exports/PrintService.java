package org.example.exports;

import com.itextpdf.layout.element.ListItem;
import org.example.dao.CompanyDao;
import org.example.dao.EmployeeDAO;
import org.example.dao.PurchaseDao;
import org.example.dto.CompanyPayloadDto;
import org.example.dto.CompanyProfitDTO;
import org.example.dto.EmployeeProfitDTO;
import org.example.dto.EmployeePurchaseTotalDTO;
import org.example.entities.*;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

// Used and adapted the print method from CITB408
public class PrintService {

    private EmployeeDAO employeeDAO;
    private PurchaseDao purchaseDao;
    private CompanyDao companyDao;

    private CompanyPayloadDto companyPayloadDto;
    private CompanyProfitDTO companyProfitDTO;

    private EmployeePurchaseTotalDTO employeePurchaseTotalDTO;
    private EmployeeProfitDTO employeeProfitDTO;


    public PrintService() {

        employeeDAO = new EmployeeDAO();
        purchaseDao = new PurchaseDao();
        companyDao = new CompanyDao();
        companyPayloadDto = new CompanyPayloadDto();
        companyProfitDTO = new CompanyProfitDTO();
        employeePurchaseTotalDTO = new EmployeePurchaseTotalDTO();
        employeeProfitDTO = new EmployeeProfitDTO();
    }

    public void printPurchaseToPdf(Purchase purchase, PurchaseDao purchaseDao) {
        String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\purchase" + purchase.getId() + ".pdf";
        try (PdfWriter writer = new PdfWriter(filePath)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    Paragraph title = new Paragraph("Purchase #" + purchase.getId())
                            .setFontSize(18)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER);
                    document.add(title);
                    document.add(new Paragraph("Arrival point: " + purchase.getArrival_point()));
                    document.add(new Paragraph("Departure point: " + purchase.getDeparture_point()));
                    document.add(new Paragraph("Start time: " + purchase.getStart_time().format(formatter)));
                    document.add(new Paragraph("End time: " + purchase.getEnd_time().format(formatter)));
                    document.add(new Paragraph("Price: " + purchase.getPrice()));
                    document.add(new Paragraph("Driver: " + purchase.getEmployee().getName() + " " + purchase.getEmployee().getFamily_name()));
                    document.add(new Paragraph("Company: " + purchase.getCompany().getName()));

                }
            }
            System.out.println("PDF created successfully at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printCompanyToPdf(Company company, CompanyDao companyDao) {
        // String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\company.pdf";
        String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\company" + company.getId() + ".pdf";
        try (PdfWriter writer = new PdfWriter(filePath)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    Paragraph title = new Paragraph("Company #" + company.getId())
                            .setFontSize(18)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER);
                    document.add(title);
                    document.add(new Paragraph("Name: " + company.getName()));
                    document.add(new Paragraph("Employees: "));
                    for (Employee employee : company.getEmployees()) {
                        document.add(new Paragraph("Name: " + employee.getName()));
                        document.add(new Paragraph("Family name: " + employee.getFamily_name()));
                    }
                    document.add(new Paragraph("Vehicles: "));
                    for (Vehicle vehicle : company.getVehicles()) {
                        document.add(new Paragraph("Vehicle plate: " + vehicle.getPlate()));
                        document.add(new Paragraph("Vehicle type: " + vehicle.getType()));
                    }
                    document.add(new Paragraph("Clients: "));
                    for (Client client : company.getClients()) {
                        document.add(new Paragraph("Name: " + client.getName()));
                        document.add(new Paragraph("Family Name: " + client.getFamily_name()));
                    }
                }
            }
            System.out.println("PDF created successfully at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printEmployeeToPdf(Employee employee, EmployeeDAO employeeDAO) {
        // String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\employee.pdf";
        String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\employee " + employee.getId() + ".pdf";
        try (PdfWriter writer = new PdfWriter(filePath)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    Paragraph title = new Paragraph("Employee #" + employee.getId())
                            .setFontSize(18)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER);
                    document.add(title);
                    document.add(new Paragraph("First name : " + employee.getName()));
                    document.add(new Paragraph("Family name : " + employee.getFamily_name()));
                    document.add(new Paragraph("EGN : " + employee.getEGN()));
                    for (Skill skill : employee.getSkills()) {
                        document.add(new Paragraph("Skill: " + skill.getType()));
                    }
                    document.add(new Paragraph("Salary: " + employee.getSalary()));

                }
            }
            System.out.println("PDF created successfully at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printCompanyPayloadToPDF(CompanyPayloadDto companyPayloadDto) {
        // String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\employee.pdf";
        String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\company " + companyPayloadDto.getCompany_id() + "payload" + companyPayloadDto.getPayload_id() + ".pdf";
        try (PdfWriter writer = new PdfWriter(filePath)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    Paragraph title = new Paragraph("Company #" + companyPayloadDto.getCompany_id())
                            .setFontSize(18)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER);
                    document.add(title);
                    document.add(new Paragraph("Company id  : " + companyPayloadDto.getCompany_id()));
                    document.add(new Paragraph("Purchase id  : " + companyPayloadDto.getPurchase_id()));
                    document.add(new Paragraph("Payload id : " + companyPayloadDto.getPayload_id()));

                }
            }
            System.out.println("PDF created successfully at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printCompanyProfitToPDF(CompanyProfitDTO companyProfitDTO) {
        // String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\employee.pdf";
        String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\company " + companyProfitDTO.getId() + " profits.pdf";
        try (PdfWriter writer = new PdfWriter(filePath)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    Paragraph title = new Paragraph("Company #" + companyProfitDTO.getId())
                            .setFontSize(18)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER);
                    document.add(title);
                    document.add(new Paragraph("Company id  : " + companyProfitDTO.getId()));
                    document.add(new Paragraph("Company name   : " + companyProfitDTO.getName()));
                    document.add(new Paragraph("Profit : " + companyProfitDTO.getProfit()));

                }
            }
            System.out.println("PDF created successfully at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printCompanyProfitIntervalToPDF(CompanyProfitDTO companyProfitDTO, LocalDate start_time, LocalDate end_time) {
        // String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\employee.pdf";
        String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\company " + companyProfitDTO.getId() + " profits from " + start_time + " to " + end_time + " .pdf";
        try (PdfWriter writer = new PdfWriter(filePath)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    Paragraph title = new Paragraph("Company #" + companyProfitDTO.getId())
                            .setFontSize(18)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER);
                    document.add(title);
                    document.add(new Paragraph("Company id  : " + companyProfitDTO.getId()));
                    document.add(new Paragraph("Company name   : " + companyProfitDTO.getName()));
                    document.add(new Paragraph("Profit : " + companyProfitDTO.getProfit()));

                }
            }
            System.out.println("PDF created successfully at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printEmployeePurchasesTotalToPDF(EmployeePurchaseTotalDTO employeePurchaseTotalDTO) {
        // String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\employee.pdf";
        String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\employee " + employeePurchaseTotalDTO.getName() + " " + employeePurchaseTotalDTO.getFamily_name() + " purchasesTotal.pdf";
        try (PdfWriter writer = new PdfWriter(filePath)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    Paragraph title = new Paragraph("Employee #" + employeePurchaseTotalDTO.getName() + " " + employeePurchaseTotalDTO.getFamily_name())
                            .setFontSize(18)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER);
                    document.add(title);
                    document.add(new Paragraph("Employee name : " + employeePurchaseTotalDTO.getName()));
                    document.add(new Paragraph("Employee surname : " + employeePurchaseTotalDTO.getFamily_name()));
                    document.add(new Paragraph("Company : " + employeePurchaseTotalDTO.getCompany_name()));
                    document.add(new Paragraph("Total purchases (count) : " + employeePurchaseTotalDTO.getCount()));

                }
            }
            System.out.println("PDF created successfully at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }public void printEmployeeProfitTotalToPDF(EmployeeProfitDTO employeeProfitDTO) {
        // String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\employee.pdf";
        String filePath = "C:\\Users\\Alex\\Desktop\\Uni\\java\\Java_single_repo\\Java_single\\src\\main\\resources\\employee " + employeeProfitDTO.getName() + " " + employeeProfitDTO.getFamily_name() + " profitTotal.pdf";
        try (PdfWriter writer = new PdfWriter(filePath)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    Paragraph title = new Paragraph("Employee #" + employeeProfitDTO.getName() + " " + employeeProfitDTO.getFamily_name())
                            .setFontSize(18)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER);
                    document.add(title);
                    document.add(new Paragraph("Employee name : " + employeeProfitDTO.getName()));
                    document.add(new Paragraph("Employee surname : " + employeeProfitDTO.getFamily_name()));
                    document.add(new Paragraph("Company : " + employeeProfitDTO.getCompany_name()));
                    document.add(new Paragraph("Total profit : " + employeeProfitDTO.getProfit()));

                }
            }
            System.out.println("PDF created successfully at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
