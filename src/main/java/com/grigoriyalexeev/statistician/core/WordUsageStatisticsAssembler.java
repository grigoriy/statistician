package com.grigoriyalexeev.statistician.core;

import java.util.List;
import java.util.Map;

public interface WordUsageStatisticsAssembler {
    Map<String, Long> assemble(List<String> rawData);
}
