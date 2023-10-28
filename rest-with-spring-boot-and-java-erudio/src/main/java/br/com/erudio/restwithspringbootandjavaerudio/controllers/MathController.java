package br.com.erudio.restwithspringbootandjavaerudio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {
    @Autowired
    private MathService mathService;
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(
            @PathVariable(value = "numberOne")String numberOne ,
            @PathVariable(value = "numberTwo")String numberTwo) throws Exception {

        return mathService.mathValue(numberOne) * mathService.mathValue(numberTwo);
    }
    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "numberOne")String numberOne ,
            @PathVariable(value = "numberTwo")String numberTwo) throws Exception {

        return mathService.mathValue(numberOne) + mathService.mathValue(numberTwo);
    }
    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction(
            @PathVariable(value = "numberOne")String numberOne ,
            @PathVariable(value = "numberTwo")String numberTwo) throws Exception {

        return mathService.mathValue(numberOne) - mathService.mathValue(numberTwo);
    }
    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(
            @PathVariable(value = "numberOne")String numberOne ,
            @PathVariable(value = "numberTwo")String numberTwo) throws Exception {

        return mathService.mathValue(numberOne) / mathService.mathValue(numberTwo);
    }
    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mean(
            @PathVariable(value = "numberOne")String numberOne ,
            @PathVariable(value = "numberTwo")String numberTwo) throws Exception {

        return (mathService.mathValue(numberOne) + mathService.mathValue(numberTwo)) / 2;
    }
    @RequestMapping(value = "/squareRoot/{numberOne}", method = RequestMethod.GET)
    public Double squareRoot(
            @PathVariable(value = "numberOne")String numberOne) throws Exception {
        Double squareRootResult = Math.sqrt(mathService.mathValue(numberOne));
        return squareRootResult ;
    }

}
