package com.chatapp.chatapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    public List<Message> getAllMessages(String receiverUsername) {
        List<Message> messageList = repository.findAll();

        // Debugging: Log the messages before filtering
        // System.out.println("All messages: " + messageList);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        // Filter messages where the authenticated user is either the sender or the receiver
        messageList = messageList.stream()
                .filter(message -> (username.equals(message.getUsername())
                && receiverUsername.equals(message.getReciverusername()))
                || (username.equals(message.getReciverusername())
                && receiverUsername.equals(message.getUsername()))
                )
                .collect(Collectors.toList());

        // Debugging: Log the messages after filtering
        // System.out.println("Filtered messages: " + messageList);
        List<Message> decodedMessages = new ArrayList<>();

        for (Message originalMessage : messageList) {
            String decoding = originalMessage.getMessages();
            String decodedMessage = decode(decoding); // Decode as a copy

            // Create a new Message object with the same properties as originalMessage
            Message decodedMessageObj = new Message();
            decodedMessageObj.setId(originalMessage.getId());  // Copy other fields as needed
            decodedMessageObj.setName(originalMessage.getName());
            decodedMessageObj.setMessages(decodedMessage);
            decodedMessageObj.setUsername(originalMessage.getUsername());
            decodedMessageObj.setReciverusername(originalMessage.getReciverusername());

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
