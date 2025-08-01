SELECT T1.ID,
    IFNULL(T2.CHILD_COUNT, 0) AS CHILD_COUNT
FROM ECOLI_DATA T1
LEFT JOIN (SELECT T1.PARENT_ID,
            COUNT(T1.PARENT_ID) AS CHILD_COUNT
        FROM ECOLI_DATA T1
        JOIN ECOLI_DATA T2 ON (T1.PARENT_ID = T2.ID)
        GROUP BY T1.PARENT_ID) T2 ON (T1.ID = T2.PARENT_ID)