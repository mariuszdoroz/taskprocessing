Simple guide for installing the task processing project using Docker. It also outlines the functionality of the REST API with sample endpoints, request, and response examples.

## Installation

### Step 1: Clone the Repository
```
git clone https://github.com/mariuszdoroz/taskprocessing.git
cd taskprocessing
```

### Step 2: Build Docker Image
```
docker build -t taskprocessing .
```

### Step 3: Run Docker Container
```
docker run -p 8080:8080 taskprocessing
```

## Usage
### REST API
1. Create new task: ```/api/task/create```

+ Method: POST
+ Description: create new task
+ Example Request: ```http://localhost:8080/api/task/create```
+ Request Body: ```{"input":"ABCD","pattern":"BCD"}```
+ Example Response: 

    ```
  {
  "id": 1,
  "input": "ABCD",
  "pattern": "BCD",
  "status": 0,
  "position": 0,
  "typos": 0
    }
  ```

2. Check task status: ```/api/task/{id}```

+ Method: GET
+ Description: check task status (works 1-10 sec after create new task)
+ Example Request: ```http://localhost:8080/api/task/status/1```
+ Request Body: ```{"input":"ABCD","pattern":"BCD"}```
+ Example Response:
 
  ```
  {
  "id": 1,
  "input": "ABCD",
  "pattern": "BCD",
  "status": 0,
  "position": 0,
  "typos": 0
  }
    ```


3. Show all tasks: ```/api/task/showAll```

+ Method: GET
+ Description: Show all tasks in db with result
+ Example Request: ```http://localhost:8080/api/task/showAll```
+ Example Response:
  ```
  [
  {
  "id": 1,
  "input": "PWFLOMVLYVNHEZEJNKSYBLDWONKRWHSP",
  "pattern": "KRA",
  "status": 100,
  "position": 26,
  "typos": 1
  },
  {
  "id": 2,
  "input": "ABCD",
  "pattern": "BCD",
  "status": 100,
  "position": 1,
  "typos": 0
  }
  ]
  ```
