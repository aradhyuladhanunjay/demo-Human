package com.example.demo.Human.HumanService;

import com.example.demo.Human.Entity.HumanEntity;
import com.example.demo.Human.HumanDTO.HumanDTO;

import java.util.List;

public interface HumanService {
    public HumanDTO addhumanentry(HumanDTO inputdata);

    public String deletehumanentry(long id);

    public HumanDTO updatehumanentry(long userid, HumanDTO hdto);

    public List<HumanDTO> gethumanentrydetails(int PageSize, int PageNo, String SortBy, String SortDir);

    public HumanDTO getentrydetailsbyid(long humanid);

}
