SELECT Request_at AS Day
    , ROUND(COUNT(Status <> 'completed' OR NULL) / COUNT(*), 2) AS `Cancellation Rate`
FROM Trips
JOIN Users
ON Users_Id = Client_Id AND Banned = 'No' AND Request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY Request_at