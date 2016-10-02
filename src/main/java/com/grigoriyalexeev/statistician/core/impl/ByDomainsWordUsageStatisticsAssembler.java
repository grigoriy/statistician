package com.grigoriyalexeev.statistician.core.impl;

import com.google.common.net.InternetDomainName;
import com.grigoriyalexeev.statistician.core.WordUsageStatisticsAssembler;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ByDomainsWordUsageStatisticsAssembler implements WordUsageStatisticsAssembler {
    @Override
    public Map<String, Long> assemble(List<String> links) {
        return links.stream()
                .distinct()
                .map(s -> {
                    String hostName = URI.create(s).getHost();
                    return InternetDomainName.from(hostName).topPrivateDomain().toString();
                })
                .collect(groupingBy(identity(), counting()));
    }
}
