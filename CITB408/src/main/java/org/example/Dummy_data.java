package org.example;

import org.example.configuration.SessionUtil;
import org.example.dao.*;
import org.example.dto.CompanyPayloadDto;
import org.example.entities.*;
import org.example.exports.PrintService;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dummy_data {
    private EmployeeDAO employeeDAO;
    private VehicleDao vehicleDao;
    private ClientDao clientDao;
    private PayloadDao payloadDao;
    private CompanyDao companyDao;
    private PurchaseDao purchaseDao;

    public Dummy_data() {
        employeeDAO = new EmployeeDAO();
        vehicleDao = new VehicleDao();
        clientDao = new ClientDao();
        payloadDao = new PayloadDao();
        companyDao = new CompanyDao();
        purchaseDao = new PurchaseDao();
    }


    public void dummy() {
        Session session = SessionUtil.getSessionFactory().openSession();

        /* Create skills */
        Skill skill1 = new Skill(1, "Bus", null, null);
        Skill skill2 = new Skill(2, "Gas", null, null);
        Skill skill3 = new Skill(3, "Freight", null, null);
        Skill skill4 = new Skill(4, "Samolet", null, null);
        Set<Skill> skills = new HashSet<>();
        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill3);
        skills.add(skill4);
        SkillDao.createSkill(skill1);
        SkillDao.createSkill(skill2);
        SkillDao.createSkill(skill3);
        SkillDao.createSkill(skill4);

        /*Create employees */
        Employee employee1 = new Employee(0, "Ivan", "Parvanov", "1234567890", 2000, null, skills, null);
        Employee employee2 = new Employee(0, "Petar", "Petrov", "2233223322", 2000, null, new HashSet<>(Arrays.asList(skill2)), null);
        Employee employee3 = new Employee(0, "Georgi", "Todorov", "7355608111", 2300, null, new HashSet<>(Arrays.asList(skill3)), null);
        Employee employee4 = new Employee(0, "Milen", "Atanasov", "3330003331", 2220, null, skills, null);
        Employee employee5 = new Employee(0, "Atanas", "Slavov", "1110001110", 5000, null, new HashSet<>(Arrays.asList(skill2)), null);
        Employee employee6 = new Employee(0, "Stefan", "Spasov", "0011223344", 1000, null, skills, null);
        Employee employee7 = new Employee(0, "Metodi", "Petrov", "1920394050", 6000, null, new HashSet<>(Arrays.asList(skill4)), null);
        Employee employee8 = new Employee(0, "Alexander", "Dimitrov", "9876543210", 10000, null, skills, null);
        Set<Employee> employeesSet1 = new HashSet<>();
        Set<Employee> employeesSet2 = new HashSet<>();
        Set<Employee> employeesSet3 = new HashSet<>();
        employeesSet1.add(employee1);
        employeesSet1.add(employee2);
        employeesSet2.add(employee3);
        employeesSet2.add(employee4);
        employeesSet3.add(employee5);
        employeesSet3.add(employee6);
        employeesSet3.add(employee7);
        employeesSet3.add(employee8);
        EmployeeDAO.createEmployee(employee1);
        EmployeeDAO.createEmployee(employee2);
        EmployeeDAO.createEmployee(employee3);
        EmployeeDAO.createEmployee(employee4);
        EmployeeDAO.createEmployee(employee5);
        EmployeeDAO.createEmployee(employee6);
        EmployeeDAO.createEmployee(employee7);
        EmployeeDAO.createEmployee(employee8);


        /*Create Vehicles */
        Vehicle vehicle1 = new Vehicle(1, "Avtobus", UnitType.People, "AB1234BA", 80, null, null);
        Vehicle vehicle2 = new Vehicle(21, "Cisterna", UnitType.Litre, "AB0011XX", 2000, null, null);
        Vehicle vehicle3 = new Vehicle(33, "Kamion", UnitType.Kilograms, "XX9911XX", 5000, null, null);
        Vehicle vehicle4 = new Vehicle(99, "Samolet", UnitType.People, "XX0000XX", 100, null, null);
        Set<Vehicle> vehicleSet1 = new HashSet<>();
        vehicleSet1.add(vehicle1);
        vehicleSet1.add(vehicle2);
        vehicleSet1.add(vehicle3);
        Set<Vehicle> vehicleSet2 = new HashSet<>();
        vehicleSet2.add(vehicle1);
        vehicleSet2.add(vehicle2);
        Set<Vehicle> vehiclesSet3 = new HashSet<>();
        vehiclesSet3.add(vehicle1);
        vehiclesSet3.add(vehicle3);
        VehicleDao.createVehicle(vehicle1);
        VehicleDao.createVehicle(vehicle2);
        VehicleDao.createVehicle(vehicle3);
        VehicleDao.createVehicle(vehicle4);


        /*Create Clients*/
        Client client1 = new Client(1, "Klient", "Klientov", null, null);
        Client client2 = new Client(2, "Metodi", "Klientov", null, null);
        Client client3 = new Client(3, "Krum", "Stoyanov", null, null);
        Client client4 = new Client(4, "Vasil", "Terziev", null, null);
        Client client5 = new Client(5, "Boris", "Bonev", null, null);
        Client client6 = new Client(6, "Boiko", "Borisov", null, null);
        ClientDao.createClient(client1);
        ClientDao.createClient(client2);
        ClientDao.createClient(client3);
        ClientDao.createClient(client4);
        ClientDao.createClient(client5);
        ClientDao.createClient(client6);


        /* Create Companies */
        Company company1 = new Company(1, "DHL", vehicleSet1, employeesSet1, null, new HashSet<>(Arrays.asList(client1)));
        Company company2 = new Company(2, "Ekont", vehicleSet2, employeesSet2, null, new HashSet<>(Arrays.asList(client2)));
        Company company3 = new Company(3, "Speedy", vehiclesSet3, employeesSet3, null, new HashSet<>(Arrays.asList(client4)));
        Company company4 = new Company(4, "Express", new HashSet<>(Arrays.asList(vehicle4)), employeesSet1, null, new HashSet<>(Arrays.asList(client6)));
        CompanyDao.createCompany(company1);
        CompanyDao.createCompany(company2);
        CompanyDao.createCompany(company3);
        CompanyDao.createCompany(company4);

        /* Create Payloads */
        Payload payload1 = new Payload(1, skill1, 100, null);
        Payload payload2 = new Payload(333, skill2, 2000, null);
        Payload payload3 = new Payload(555, skill3, 5000, null);
        Payload payload4 = new Payload(888, skill4, 100, null);
        PayloadDao.createPayload(payload1);
        PayloadDao.createPayload(payload2);
        PayloadDao.createPayload(payload3);
        PayloadDao.createPayload(payload4);


        Receipt receipt1 = new Receipt(1, client1, null);
        Receipt receipt2 = new Receipt(2, client3, null);
        Receipt receipt3 = new Receipt(3, client2, null);
        //  Receipt receipt4 = new Receipt(4,client5, null);
        ReceiptDao.createReceipt(receipt1);
        ReceiptDao.createReceipt(receipt2);
        ReceiptDao.createReceipt(receipt3);
        // ReceiptDao.createReceipt(receipt4);

        /* Create Purchases */
        Purchase purchase1 = new Purchase(1, LocalDateTime.now(), LocalDateTime.now().plusDays(1), "Belene", 100, "Lovech", employee1, vehicle1, company1, payload1, new HashSet<>(Arrays.asList(client1)), new HashSet<>(Arrays.asList(receipt1)));
        Purchase purchase2 = new Purchase(2, LocalDateTime.now(), LocalDateTime.now().plusDays(2), "Pleven", 140, "Ruse", employee2, vehicle2, company3, payload3, new HashSet<>(Arrays.asList(client2)), new HashSet<>(Arrays.asList(receipt3)));
        Purchase purchase3 = new Purchase(3, LocalDateTime.now().minusDays(6), LocalDateTime.now().plusDays(11), "Sliven", 111, "Vratsa", employee5, vehicle3, company3, payload2, new HashSet<>(Arrays.asList(client2)), new HashSet<>(Arrays.asList(receipt2)));
        Purchase purchase4 = new Purchase(4, LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(13), "Belene", 111, "Vratsa", employee7, vehicle4, company4, payload4, new HashSet<>(Arrays.asList(client5)), null);
        PurchaseDao.createPurchase(purchase1);
        PurchaseDao.createPurchase(purchase2);
        PurchaseDao.createPurchase(purchase3);
        PurchaseDao.createPurchase(purchase4);
        receipt1.setPurchases(purchase1);
        receipt2.setPurchases(purchase3);
        receipt3.setPurchases(purchase2);
        //receipt4.setPurchases(purchase4);
        ReceiptDao.updateReceipts(receipt1);
        ReceiptDao.updateReceipts(receipt2);
        ReceiptDao.updateReceipts(receipt3);
        //ReceiptDao.updateReceipts(receipt4);


        /* Create Receipts */





        /*Updating things because of the initialization catch 22 */
        employee1.setCompany(company1);
        employee2.setCompany(company1);
        employee3.setCompany(company2);
        employee4.setCompany(company2);
        employee5.setCompany(company3);
        employee6.setCompany(company3);
        employee7.setCompany(company4);
        employee8.setCompany(company4);

        EmployeeDAO.updateEmployee(employee1);
        EmployeeDAO.updateEmployee(employee2);
        EmployeeDAO.updateEmployee(employee3);
        EmployeeDAO.updateEmployee(employee4);
        EmployeeDAO.updateEmployee(employee5);
        EmployeeDAO.updateEmployee(employee6);
        EmployeeDAO.updateEmployee(employee7);
        EmployeeDAO.updateEmployee(employee8);


        payload1.setPurchases(purchase1);
        payload2.setPurchases(purchase2);
        payload3.setPurchases(purchase3);
        payload4.setPurchases(purchase4);

        payload1.setSkills(skill1);
        payload2.setSkills(skill2);
        payload3.setSkills(skill3);
        payload4.setSkills(skill4);

        PayloadDao.updatePayload(payload1);
        PayloadDao.updatePayload(payload2);
        PayloadDao.updatePayload(payload3);
        PayloadDao.updatePayload(payload4);


        vehicle1.setCompany(company1);
        vehicle2.setCompany(company2);
        vehicle3.setCompany(company3);
        vehicle4.setCompany(company4);
        VehicleDao.updateVehicle(vehicle1);
        VehicleDao.updateVehicle(vehicle2);
        VehicleDao.updateVehicle(vehicle3);
        VehicleDao.updateVehicle(vehicle4);

        company3.setClients(new HashSet<>(Arrays.asList(client2)));
        CompanyDao.updateCompany(company3);

        company4.setClients(new HashSet<>(Arrays.asList(client5, client6)));
        CompanyDao.updateCompany(company4);










        /*Loading everything */
        CompanyDao.getCompanies().stream().forEach(System.out::println);
        EmployeeDAO.getEmoplyees().stream().forEach(System.out::println);
        VehicleDao.getVehicles().stream().forEach(System.out::println);
        ClientDao.getClients().stream().forEach(System.out::println);
        PayloadDao.getPayloads().stream().forEach(System.out::println);
        ReceiptDao.getReceipts().stream().forEach(System.out::println);
        PurchaseDao.getPurchases().stream().forEach(System.out::println);
        SkillDao.getSkills().stream().forEach(System.out::println);


        /*Adding the required queries from point 7 */
        EmployeeDAO.getEmployeesBySkill("Bus").stream().forEach(System.out::println);//you can input a skill's type by string like skill1.getType() instead of directly
        PurchaseDao.getPurchaseByArrivalPoint("Belene").stream().forEach(System.out::println);
        CompanyDao.getCompanyByProfit(company3.getId()).stream().forEach(System.out::println);

        /* Printing data for point 8 */
        PrintService printService = new PrintService();
        printService.printPurchaseToPdf(purchase1, purchaseDao);
        printService.printCompanyToPdf(company1, companyDao);
        printService.printEmployeeToPdf(employee8, employeeDAO);


        /* Point 9 requirements */

        PayloadDao.getCompanyPayloads(company1.getId());
        CompanyDao.getCompanyByProfit(company1.getId());
        CompanyDao.getCompanyByProfitInterval(company1.getId(), LocalDateTime.now().minusDays(300), LocalDateTime.now().plusDays(500));
        EmployeeDAO.getEmployeePurchasesTotal(employee1.getId());
        EmployeeDAO.getEmployeeProfitTotal(employee1.getId());

        /* Printint data from point 9 */
        /**
         * Print Service takes a single CompanyProfitDTO
         * getCompanyByProfit gives back a List of CompanyProfitDTOs in case I give it multiple companies
         * so when printing it i just select the first element (since there aren't any other companies if I give it a single element)
         * */
        printService.printCompanyProfitToPDF( CompanyDao.getCompanyByProfit(company1.getId()).get(0));

        /** Here we just print the results while the [getCompanyByProfitInterval]  does the actual filtering
         * I Use this LocalDateTime.now().toLocalDate() in order to shorten the file name but i work even without it */
        printService.printCompanyProfitIntervalToPDF(   CompanyDao.getCompanyByProfitInterval(company1.getId(), LocalDateTime.now().minusDays(300), LocalDateTime.now().plusDays(500)).get(0) ,LocalDateTime.now().minusDays(300).toLocalDate(), LocalDateTime.now().plusDays(500).toLocalDate()      );

        /** Same Idea as in PrintCompanyProfit */
        printService.printEmployeePurchasesTotalToPDF(EmployeeDAO.getEmployeePurchasesTotal(employee1.getId()).get(0));

        /** Same Idea as in PrintCompanyProfit */
        printService.printEmployeeProfitTotalToPDF(EmployeeDAO.getEmployeeProfitTotal(employee1.getId()).get(0));

        /** Here the problem is the opposite of CompanyProfit - I made a list and iterated it */
        List<CompanyPayloadDto> companyPayloadDtoList = PayloadDao.getCompanyPayloads(company1.getId());
        for (int i = 0; i < companyPayloadDtoList.size(); i++) {
            printService.printCompanyPayloadToPDF(companyPayloadDtoList.get(i));
        }
    }


}



