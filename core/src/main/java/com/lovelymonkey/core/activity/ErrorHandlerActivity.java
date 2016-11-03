package com.lovelymonkey.core.activity;

import java.util.LinkedList;
import java.util.List;

import org.stream.core.component.Activity;
import org.stream.core.component.ActivityResult;
import org.stream.core.execution.ExecutionRecord;
import org.stream.core.execution.WorkFlowContext;

import lombok.extern.slf4j.Slf4j;

/**
 * Default error handler activity. Will be executed automatically by the Work-flow {@link Engine} if this activity is
 * configured into the graph.
 * @author guanxwei
 *
 */
@Slf4j
public class ErrorHandlerActivity extends Activity {

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivityResult act() {
        Exception e = WorkFlowContext.extractException();
        List<ExecutionRecord> records = WorkFlowContext.getRecords() == null ? WorkFlowContext.getRecords() : new LinkedList<>();
        records.forEach(record -> {
            log.info(record.getDescription());
        });
        if (e != null) {
            log.warn(e.getMessage());
        }
        return ActivityResult.SUCCESS;
    }

}
