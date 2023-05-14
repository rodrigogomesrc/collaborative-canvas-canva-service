package br.ufrn.dimap.collaborativecanvas.canvaservice.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
public class UseVirtualThreadCondition implements Condition {

    @Override
    public boolean matches(@NotNull ConditionContext context, @NotNull AnnotatedTypeMetadata metadata) {
        boolean useVirtualThreads = Boolean.TRUE.equals(context.getEnvironment().getProperty("app.use-virtual-threads", Boolean.class));
        System.out.println("=============== USING VIRTUAL THREADS: " + useVirtualThreads);
        return false;
    }
}
