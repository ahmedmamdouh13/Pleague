package com.ahmedmamdouh13.theleague.domain.mapper

import com.ahmedmamdouh13.theleague.domain.model.DomainModel
import io.reactivex.Single
import javax.inject.Inject

class ScheduleMapper @Inject constructor() {

    fun getMatchesInScheduleForm(modelList: List<DomainModel>): Map<String, List<DomainModel>>{
            val map = HashMap<String,ArrayList<DomainModel>>()
            for (domainModel in modelList) {
                map.getOrPut(domainModel.date,{
                    arrayListOf()
                }).add(domainModel)
            }
            return  map
    }

}