package com.dxc.app.cucumber.stepdefs;

import com.dxc.app.JhipstergatwayApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = JhipstergatwayApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
