package com.canalplus.automaticien.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict=true, features = "classpath:feature", glue = { "com.canalplus.automaticien" })
public class CucumberIt {

}
