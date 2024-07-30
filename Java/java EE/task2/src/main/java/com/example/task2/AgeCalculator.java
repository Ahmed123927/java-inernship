package com.example.task2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@WebServlet("/ageCalculator")
public class AgeCalculator extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateOfBirth = request.getParameter("dob");
        if (dateOfBirth != null){
            LocalDate dob = LocalDate.parse(dateOfBirth);
            LocalDate now = LocalDate.now();

            long years = ChronoUnit.YEARS.between(dob, now);
            dob = dob.plusYears(years);

            long months = ChronoUnit.MONTHS.between(dob, now);
            dob = dob.plusMonths(months);

            long days = ChronoUnit.DAYS.between(dob, now);
            dob = dob.plusDays(days);

            long hours = ChronoUnit.HOURS.between(LocalDateTime.of(dob, java.time.LocalTime.MIN), LocalDateTime.now());

            request.setAttribute("dob", dateOfBirth);
            request.setAttribute("years", years);
            request.setAttribute("months", months);
            request.setAttribute("days", days);
            request.setAttribute("hours", hours);
            request.getRequestDispatcher("/ageResult.jsp").forward(request, response);

        }
    }
}
