WITH SALES AS (
    SELECT BOOK_ID,
        SUM(SALES) AS CNT
    FROM BOOK_SALES
    WHERE EXTRACT(YEAR FROM SALES_DATE) = 2022
    AND EXTRACT(MONTH FROM SALES_DATE) = 1
    GROUP BY BOOK_ID
)

SELECT B.AUTHOR_ID,
    A.AUTHOR_NAME,
    B.CATEGORY,
    SUM(B.PRICE * S.CNT) AS TOTAL_SALES
FROM BOOK B
JOIN AUTHOR A ON (B.AUTHOR_ID = A.AUTHOR_ID)
JOIN SALES S ON (B.BOOK_ID = S.BOOK_ID)
GROUP BY B.AUTHOR_ID, A.AUTHOR_NAME, B.CATEGORY
ORDER BY B.AUTHOR_ID ASC, B.CATEGORY DESC