This is a small project I made as part of learning Spring REST and Spring JPA.
There is a User Entity with properties like name, birthday, about. 
CRUD operations can be performed using REST APIs and MySQL database.

One User can create multiple posts, so there is One to many relationship between User and Post entity.
Each post can be retrieved by post id, deleted, editied etc.

This application runs on localhost:8080 and mysql details needs to be updated in application.properties

Sample GET users endpoint details:

```
http://localhost:8080/users
```
Sample Response: 
```
[
    {
        "name": "Aishwarya",
        "userId": 1,
        "about": "SDE",
        "birthday": "1994-12-17",
        "posts": [
            {
                "postID": 8,
                "description": "Hi all! This is my second post & I'm very happy about it..."
            },
            {
                "postID": 9,
                "description": "Hi all! This is my First post"
            }
        ]
    },
    {
        "name": "Dipti",
        "userId": 2,
        "about": "School Teacher",
        "birthday": "1987-04-17",
        "posts": []
    },
    {
        "name": "Pranav",
        "userId": 3,
        "about": "Mechanical Engineer",
        "birthday": "1995-01-25",
        "posts": [
            {
                "postID": 10,
                "description": "Hi all! This is my First post"
            },
            {
                "postID": 11,
                "description": "Hi all! This is my Second post"
            }
        ]
    }
]
```
