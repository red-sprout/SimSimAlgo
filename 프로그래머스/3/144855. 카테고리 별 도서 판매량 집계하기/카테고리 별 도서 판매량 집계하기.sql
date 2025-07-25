SELECT B.CATEGORY,
    SUM(S.SALES) AS TOTAL_SALES
FROM BOOK B
JOIN BOOK_SALES S ON (B.BOOK_ID = S.BOOK_ID)
WHERE EXTRACT(YEAR FROM S.SALES_DATE) = 2022
AND EXTRACT(MONTH FROM S.SALES_DATE) = 1
GROUP BY B.CATEGORY
ORDER BY B.CATEGORY ASC