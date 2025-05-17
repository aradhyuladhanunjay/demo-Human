package com.example.demo.Human.HumanService;

import com.example.demo.Human.Entity.HumanEntity;
import com.example.demo.Human.Exception.Responsenotfound;
import com.example.demo.Human.HumanDTO.HumanDTO;
import com.example.demo.Human.Repository.HumanEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HumanServiceImp implements HumanService{

    private HumanEntityRepository HES;

    public HumanServiceImp(HumanEntityRepository HES) {
        this.HES = HES;
    }

    @Override
    public HumanDTO addhumanentry(HumanDTO inputdata) {
//        HumanEntity HE= new HumanEntity();
//        HE.setId(inputdata.getId());
//        HE.setMobile(inputdata.getMobile());
//        HE.setName(inputdata.getName());
//        HE.setEmailId(inputdata.getEmailId());

        HumanEntity HE=HES.save(dtotoentity(inputdata));
//        HumanDTO hdto= new HumanDTO();
//        hdto.setEmailId(HE.getEmailId());
//        hdto.setId(HE.getId());
//        hdto.setName(HE.getName());
//        hdto.setMobile(HE.getMobile());
//        System.out.println(HE.getId());
//        System.out.println(HE.getName());
//        System.out.println(HE.getEmailId());
//        System.out.println(HE.getMobile());


        return Entitytodto(HE);
    }

    @Override
    public String deletehumanentry(long id) {
        HES.deleteById(id);
        return "user deleted";
    }

    @Override
    public HumanDTO updatehumanentry(long userid, HumanDTO hdto) {
        HumanEntity HOS= null;
       Optional<HumanEntity>HE= HES.findById(userid);
       if (HE.isPresent()){
           HOS = HE.get();
           HOS.setName(hdto.getName());
           HOS.setMobile(hdto.getMobile());
           HOS.setEmailId(hdto.getEmailId());
           HES.save(HOS);
           return Entitytodto(HOS);

       }
       else{
           throw new Responsenotfound("unable to find the human details with this id"+ userid);
       }
//       HumanEntity HOS = HE.get();
//       HOS.setName(hdto.getName());
//       HOS.setMobile(hdto.getMobile());
//       HOS.setEmailId(hdto.getEmailId());
//       HES.save(HOS);
//       System.out.println(HOS.getName());


    }

    @Override
    public List<HumanDTO> gethumanentrydetails(int PageSize, int PageNo, String SortBy, String SortDir) {
        Pageable pageable=null;
        if (SortDir.equalsIgnoreCase("asc")){
            pageable= PageRequest.of(PageNo, PageSize, Sort.by(SortBy).ascending());
        }
        else{
            pageable=PageRequest.of(PageNo, PageSize, Sort.by(SortBy).descending());
        }

        Page<HumanEntity> all = HES.findAll(pageable);
        List<HumanEntity> content = all.getContent();
        List<HumanDTO> humadto=content.stream().map(p->Entitytodto(p)).collect(Collectors.toList());
        return humadto;
    }

    @Override
    public HumanDTO getentrydetailsbyid(long humanid) {
        HumanEntity HEDA=HES.findById(humanid).orElseThrow(() -> new Responsenotfound("this id is not present"+ humanid));
        HumanDTO dto =new HumanDTO();
        dto.setId(HEDA.getId());
        dto.setName(HEDA.getName());
        dto.setEmailId(HEDA.getEmailId());
        dto.setMobile(HEDA.getMobile());
        return dto;
    }

    //DTO to Entity
    public HumanEntity dtotoentity(HumanDTO humanDTO){
        HumanEntity HE= new HumanEntity();
        HE.setId(humanDTO.getId());
        HE.setName(humanDTO.getName());
        HE.setEmailId(humanDTO.getEmailId());
        HE.setMobile(humanDTO.getMobile());
        return HE;
    }
    // Entity to DTO
    public HumanDTO Entitytodto(HumanEntity HE){
        HumanDTO humanDTO = new HumanDTO();
        humanDTO.setId(HE.getId());
        humanDTO.setName(HE.getName());
        humanDTO.setEmailId(HE.getEmailId());
        humanDTO.setMobile(HE.getMobile());
        return humanDTO;
    }
}
