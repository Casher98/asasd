package edu.bsu.cs445.archdemo;

import com.google.inject.AbstractModule;
import edu.bsu.cs445.archdemo.model.JaxbParser;
import edu.bsu.cs445.archdemo.model.DomaDataParser;

public class ContentDmModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DomaDataParser.class).to(JaxbParser.class);
    }
}
