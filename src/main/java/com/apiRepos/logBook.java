package com.apiRepos;

import java.util.List;
import com.model.Log;

public interface logBook {

    List<Log> findAllLogs();

    void formatLogsTable();
}
