WITH TIME_TABLE AS (
    SELECT LEVEL - 1 AS HOUR
    FROM DUAL
    CONNECT BY LEVEL <= 24
), ANIMAL_LIST AS (
    SELECT TO_CHAR(DATETIME, 'HH24') AS HOUR,
        COUNT(ANIMAL_ID) AS COUNT
    FROM ANIMAL_OUTS
    GROUP BY TO_CHAR(DATETIME, 'HH24')
    ORDER BY HOUR
)

SELECT T.HOUR,
    NVL(L.COUNT, 0) AS COUNT
FROM TIME_TABLE T
LEFT JOIN ANIMAL_LIST L ON (T.HOUR = L.HOUR)
ORDER BY HOUR