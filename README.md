## Chat App

## Chat App Version 1.0

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

### Advantages:
- **Data Integrity**: The use of Hamming code ensures that messages are accurate and reliable, even if minor errors occur in storage.
- **Privacy**: One-to-one chatting ensures that conversations are private and secure.
- **Robustness**: The system can correct minor errors without user intervention, enhancing the reliability of the communication platform.






