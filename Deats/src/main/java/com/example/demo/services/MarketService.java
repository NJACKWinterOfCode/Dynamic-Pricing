package com.example.demo.services;

import com.example.demo.exchanges.GetDemandRequest;
import com.example.demo.exchanges.GetMarketsRequest;
import com.example.demo.exchanges.GetMarketsResponse;

public interface MarketService {



    GetMarketsResponse findMarketsCloseBy(GetMarketsRequest getMarketsRequest);

    Integer findDemand(GetDemandRequest getDemandRequest);
}
