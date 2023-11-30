package com.devlach.java_job_interview;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CallbacksExample {

    public static void main(String[] args) throws CloneNotSupportedException {

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


        System.out.println("Cloning example");
        Button button2 = new Button("Click me 2");
        System.out.println("button2 = " + button2);
        Button button3 = button2;
        button3.setLabel("Click me 3");
        System.out.println("button3 = " + button3);
        Button button4 = button3.copy();
        button4.setLabel("Click me 4");
        System.out.println("button4 = " + button4);
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
    
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Button{" +
                "onClickListener=" + onClickListener +
                ", label='" + label + '\'' +
                '}';
    }

    public Button copy() {
        return new Button(this.label);
    }
}
