package com.lovelymonkey.core.activity;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.stream.core.component.ActivityRepository;
import org.stream.core.execution.DefaultEngine;
import org.stream.core.execution.Engine;
import org.stream.core.execution.GraphContext;
import org.stream.core.helper.GraphLoader;

import com.google.common.collect.ImmutableList;

/**
 * Activity configuration file.
 * @author guanxwei
 *
 */
@Configuration
public class ActivityConfiguration {

    /**
     * GraphContext bean configuration. Initiate a singleton instance of {@link GraphContext}
     * into the web application.
     *
     * @return GraphContext instance.
     */
    @Bean
    public GraphContext getGraphContext() {
        GraphContext graphContext =  new GraphContext();
        graphContext.setActivityRepository(getActivityRepository());
        return graphContext;
    }

    /**
     * Activity Repository bean configuration. Initiate a singleton instance of {@link ActivityRepository}
     * into the web application.
     *
     * @return ActivityRepository instance.
     */
    @Bean
    public ActivityRepository getActivityRepository() {
        return new ActivityRepository();
    }

    /**
     * GraphLoader bean configuration. Initiate a singleton instance of {@link GraphLoader}
     * into the web application. The {@link GraphLoader#init()} method will be invoked automatically
     * after the bean is initiated by the Spring container, the purpose is to load the graph definition
     * file and assemble workable graphs so that the {@link Engine} instance can invoke them for
     * individual request.
     *
     * @return GraphLoader instance.
     */
    @Bean(initMethod = "init")
    public GraphLoader getGraphLoader() {
        GraphLoader graphLoader = new GraphLoader();
        graphLoader.setGraphContext(getGraphContext());
        graphLoader.setGraphFilePaths(graphs());
        return graphLoader;
    }

    /**
     * Engine bean configuration. Initiate a singleton instance of {@link Engine}, currently uses the default implementation {@link DefaultEngine}.
     *
     * @return Engine instance.
     */
    @Bean
    public Engine getWorkflowEngine() {
        return new DefaultEngine();
    }

    /**
     * Private method providing the graph definition file paths, used only by {@code ActivityConfiguration#getGraphLoader()}.
     *
     * @return Graph definition file path list.
     */
    private List<String> graphs() {
        ImmutableList<String> graphs = ImmutableList.<String>builder()
                .add("LoadDashboardGraph.graph")
                .add("LoadWidgetGraph.graph")
                .build();
        return graphs.subList(0, graphs.size());
    }
}
