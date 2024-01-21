package br.com.erudio.restwithspringbootandjavaerudio.controllers;

import br.com.erudio.restwithspringbootandjavaerudio.SimpleMath;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


@RestController
public class MathController {

    private final SimpleMath simpleMath = new SimpleMath();
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(
            @PathVariable(value = "numberOne") Double numberOne,
            @PathVariable(value = "numberTwo") Double numberTwo) throws Exception {

        return simpleMath.multiplication(numberOne, numberTwo);
    }

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "numberOne") Double numberOne,
            @PathVariable(value = "numberTwo") Double numberTwo) throws Exception {

        return simpleMath.sum(numberOne, numberTwo);
    }

    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction(
            @PathVariable(value = "numberOne") Double numberOne,
            @PathVariable(value = "numberTwo") Double numberTwo) throws Exception {

        return simpleMath.subtraction(numberOne, numberTwo);
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(
            @PathVariable(value = "numberOne") Double numberOne,
            @PathVariable(value = "numberTwo") Double numberTwo) throws Exception {

        return simpleMath.division(numberOne, numberTwo);
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mean(
            @PathVariable(value = "numberOne") Double numberOne,
            @PathVariable(value = "numberTwo") Double numberTwo) throws Exception {

        return simpleMath.mean(numberOne, numberTwo);
    }

    @RequestMapping(value = "/squareRoot/{numberOne}", method = RequestMethod.GET)
    public Double squareRoot(
            @PathVariable(value = "numberOne") Double number) throws Exception {

        return Math.sqrt(number);
    }

}
