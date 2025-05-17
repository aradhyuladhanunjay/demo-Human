package com.example.demo.Human.HumanController;


import com.example.demo.Human.Entity.HumanEntity;
import com.example.demo.Human.HumanDTO.HumanDTO;
import com.example.demo.Human.HumanService.HumanService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/humanrestcontroller/v2/api")
public class HumanController {


    private HumanService HS;

    public HumanController(HumanService HS) {
        this.HS = HS;
    }

    //http://localhost:8080/humanrestcontroller/v2/api/addentry
    @PostMapping("/addentry")
    public ResponseEntity<HumanDTO> addentry(@RequestBody HumanDTO inputdata){
        HumanDTO HDTO=HS.addhumanentry(inputdata);

        return new ResponseEntity<>(HDTO, HttpStatus.CREATED);
    }

    //http://localhost:8080/humanrestcontroller/v2/api/deleteentry
    @DeleteMapping
    public String deleteentry(@RequestParam long id){
        HS.deletehumanentry(id);
        return "user got deleted";

    }

    //http://localhost:8080/humanrestcontroller/v2/api/{userid}
    @PutMapping("/{userid}")
    public ResponseEntity<HumanDTO> updateentry(@PathVariable long userid, @RequestBody HumanDTO hdto){
        HumanDTO hresponse=HS.updatehumanentry(userid, hdto);
        return new ResponseEntity<>(hresponse, HttpStatus.OK);
    }

    //http://localhost:8080/humanrestcontroller/v2/api/getentrydetails
    @GetMapping("/getentrydetails")
    public ResponseEntity<List<HumanDTO>> getentrydetails(@RequestParam(name="PageSize", defaultValue = "0", required = false) int PageSize,
                                                          @RequestParam(name="PageNo", defaultValue = "0", required = false) int PageNo,
                                                          @RequestParam(name="SortBy", defaultValue = "id", required = false) String SortBy,
                                                          @RequestParam(name="SortDir", defaultValue = "id", required = false) String SortDir){

        List<HumanDTO> humanList=HS.gethumanentrydetails(PageSize,PageNo, SortBy, SortDir);
        return new ResponseEntity<>(humanList, HttpStatus.OK);
    }


    //http://localhost:8080/humanrestcontroller/v2/api/getentrydetailsbyid
    @GetMapping("/getentrydetailsbyid")
    public ResponseEntity<HumanDTO> getentrydetailsbyid(long humanid){
        HumanDTO dto=HS.getentrydetailsbyid(humanid);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
