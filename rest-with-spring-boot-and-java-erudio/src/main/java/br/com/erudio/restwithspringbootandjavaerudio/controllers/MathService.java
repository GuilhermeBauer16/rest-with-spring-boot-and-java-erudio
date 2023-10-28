package br.com.erudio.restwithspringbootandjavaerudio.controllers;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class MathService {
    public Double mathValue(String numberOne) throws Exception {
        if (!isNumeric(numberOne)) {
            throw new UnsupportedOperationException("Please set a numeric value!");

        }

        return convertToDouble(numberOne);

    }

    private Boolean isNumeric(String strNumber) {
        if (strNumber == null) {
            throw new RuntimeException();
        }
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private Double convertToDouble(String strNumber) throws Exception {
        if (strNumber == null) {
            throw new RuntimeException();
        }
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) {
            return Double.parseDouble(number);
        }
        return 0D;
    }
}

