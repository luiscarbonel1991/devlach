package com.devlach.java_job_interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CallbacksExample {

    public static void main(String[] args) {

        Button button = new Button("Click me");
        button.setOnClickListener(() -> System.out.println("Button clicked"));
        button.handleClick();
        // Output:
        // Click me
        // Button clicked

        // Comparator example
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Bob");
        names.add("Alice");
        names.add("Diana");
        names.add("Mike");
        names.add("Robert");
        Comparator<String> lenthComparator = Comparator.comparingInt(String::length);
        names.sort(lenthComparator);
        System.out.println(names);

    }
}

class Button {
    private Runnable onClickListener;
    private String label;

    public Button(String label) {
        // initialize the button
        this.label = label;
        System.out.println("Button created: " + label);
    }

    public void setOnClickListener(Runnable onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void handleClick() {
        if (onClickListener != null) {
            onClickListener.run();
        }
    }
}
