package com.taxi.oro.cliente.datepicker;

import org.joda.time.DateTime;


public interface DatePickerListener {
    void onDateSelected(DateTime dateSelected);
}