package com.example.lottery;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/lottery")
public class ItemController {

    private static final String UPLOADED_FOLDER = "src/main/resources/static/images/";
    private static final double FIXED_TICKET_PRICE = 2.0;
    private static final int MIN_TICKETS = 1; // Minimum tickets to start the draw

    private List<LotteryItem> items = new ArrayList<>();

    @PostMapping("/add")
    public String addItem(
            @RequestParam("type") String type,
            @RequestParam("name") String name,
            @RequestParam("ticketPrice") double ticketPrice,
            @RequestParam("minTickets") int minTickets,
            @RequestParam("paymentInfo") String paymentInfo,
            @RequestParam("image") MultipartFile image) {

        // Validate ticket price
        if (ticketPrice != FIXED_TICKET_PRICE) {
            return "Ticket price must be $2";
        }

        // Validate minimum tickets
        if (minTickets < MIN_TICKETS) {
            return "Minimum number of tickets must be at least " + MIN_TICKETS;
        }

        // Save the file locally
        if (!image.isEmpty()) {
            try {
                // Create the upload directory if it doesn't exist
                File dir = new File(UPLOADED_FOLDER);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Save the file to the upload directory
                Path path = Paths.get(UPLOADED_FOLDER + image.getOriginalFilename());
                Files.write(path, image.getBytes());

                LotteryItem item = new LotteryItem(type, name, ticketPrice, minTickets, 0, image.getOriginalFilename(), paymentInfo);
                items.add(item);

                return "Item added successfully!";
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed to upload image";
            }
        } else {
            return "Image file is empty";
        }
    }

    @GetMapping("/items")
    public List<LotteryItem> getItems() {
        return items;
    }
}