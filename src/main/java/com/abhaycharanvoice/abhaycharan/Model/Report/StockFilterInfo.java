package com.abhaycharanvoice.abhaycharan.Model.Report;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StockFilterInfo {
    private Boolean single;
    private Boolean invcAndNtDt;
    private String invoiceNo;
    private LocalDate invoiceDate;
    private LocalDate fromDate;
    private LocalDate toDate;
}
