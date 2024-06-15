package com.chatapp.chatapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.chatapp.algorithm.HammingDecode;
import com.chatapp.chatapp.algorithm.HammingEncode;
import com.chatapp.chatapp.dao.MessageRepository;
import com.chatapp.chatapp.model.Message;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private HammingEncode hammingEncode;

    @Autowired
    private HammingDecode hammingDecode;

    public Message create(Message message) {
        // Encode the message
        String encodedMessage = encoding(message.getMessages());

        // Set the encoded message
        message.setMessages(encodedMessage);

        // Save the encoded message to the repository
        return repository.save(message);
    }

    public String encoding(String s) {
        StringBuilder encodedMsg = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            String encodedCharacter = hammingEncode.hamingEcode(String.valueOf(s.charAt(i)));
            encodedMsg.append(encodedCharacter);
        }

        return encodedMsg.toString();
    }
















    public List<Message> getAllMessages() {
        List<Message> messageList = repository.findAll();
        List<Message> decodedMessages = new ArrayList<>();

        for (Message originalMessage : messageList) {
            String decoding = originalMessage.getMessages();
            String decodedMessage = decode(decoding); // Decode as a copy

            // Create a new Message object with the same properties as originalMessage
            Message decodedMessageObj = new Message();
            decodedMessageObj.setId(originalMessage.getId());  // Copy other fields as needed
            decodedMessageObj.setName(originalMessage.getName());
            decodedMessageObj.setMessages(decodedMessage);

            // Add the decoded message object to the new list
            decodedMessages.add(decodedMessageObj);
        }

        return decodedMessages;
    }

    public String decode(String s) {
        StringBuilder result = new StringBuilder();
        int bitpass = 12;

        for (int i = 0; i < s.length(); i += bitpass) {
            String everycar = s.substring(i, Math.min(i + bitpass, s.length()));
            if (everycar.length() == bitpass) {
                try {
                    result.append(hammingDecode.hdecode(everycar));
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping invalid substring: " + everycar);
                }
            } else {
                System.err.println("Skipping substring with invalid length: " + everycar);
            }
        }

        return result.toString();
    }

}