## Chat App

### Control and Running This App:

1. **Download and Setup**

   - Clone the repository:
     ```sh
     git clone https://github.com/fmahadyBD/chat_app.git
     ```
   - Open the project in VS Code or any other IDE.
   - Use Postman to register a new user at:

     ```
     http://localhost:yourPortNumber/register/user
     ```

     Example:

     ```sh
     http://localhost:8080/register/user
     ```

   - Example request body:
     ```json
     {
       "name": "Mahady Hasan Fahim",
       "username": "fmahadybd",
       "password": "123",
       "role": "USER"
     }
     ```
     ```json
     {
       "name": "ABCD",
       "username": "abcd",
       "password": "123",
       "role": "USER"
     }
     ```

2. **Run in Browser:**
   - Run the `ChatAppApplication.java` file.
   - Go to your browser and open:
     ```
     http://localhost:8080/login
     ```
   - Enter your username and password.
   - Now you can send messages.

### REST Endpoint:

**Register a new user:**

```sh
http://localhost:8080/register/user
```

## Chat App Version 2.0

#### Add new features:

1. **Show All User**
   - Allows to sent message every user by user
   - Every User Can sent message each-other

### View:

## Chat App Version 1.0

#### View:

![image](https://github.com/fmahadyBD/chat_app/assets/109776849/5a8b30ce-4083-4f34-94c1-6cc496993599)

#### Features:

1. **User Registration**:

   - Allows new users to register for the chat application.
   - Likely includes functionalities such as username and password setup, and possibly email verification.

2. **One-to-One Chatting**:

   - Enables users to send and receive messages in a private, one-on-one setting.
   - Messages are stored in a database.

3. **Error Detection and Correction using Hamming Code**:
   - **Message Encoding**:
     - Messages are converted to ASCII binary format before being stored in the database.
     - Each message is then encoded using Hamming code, which is an error-detecting and error-correcting code.
   - **Error Correction**:
     - If a single bit in the stored message changes (e.g., due to a database error), the Hamming code can detect and correct the error when the message is retrieved from the database.

### Detailed Functionality:

1. **Registration Process**:

   - Users sign up by providing necessary details.
   - Information is stored securely in the database.

2. **Sending a Message**:

   - A message is converted from text to its ASCII binary representation.
   - The binary message is encoded using Hamming code.
   - The encoded message is stored in the database.

3. **Receiving a Message**:
   - The encoded message is retrieved from the database.
   - The Hamming code is used to check for and correct any single-bit errors.
   - The corrected binary message is converted back to its original text form and displayed to the user.
