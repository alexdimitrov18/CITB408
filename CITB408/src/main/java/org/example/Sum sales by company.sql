

/* select all purchases */
SELECT * FROM java_single_schema.purchase;
/* select all receipts */
SELECT * FROM java_single_schema.receipt;
/* groups by company and sums all purchases without checking receipt */
SELECT SUM(price), company_id, name FROM java_single_schema.purchase left join java_single_schema.company on company_id = java_single_schema.company.id group by java_single_schema.company.name ;

/* Groups by company, checks if there is a receipt(so its paid) and then sums the prices */
SELECT SUM(java_single_schema.purchase.price) AS total_price, java_single_schema.company.id AS company_id, java_single_schema.company.name
FROM java_single_schema.purchase
LEFT JOIN java_single_schema.company ON java_single_schema.purchase.company_id = java_single_schema.company.id
LEFT JOIN java_single_schema.receipt ON java_single_schema.receipt.purchases_id = java_single_schema.purchase.id
WHERE java_single_schema.receipt.purchases_id IS NOT NULL
GROUP BY java_single_schema.company.id, java_single_schema.company.name;