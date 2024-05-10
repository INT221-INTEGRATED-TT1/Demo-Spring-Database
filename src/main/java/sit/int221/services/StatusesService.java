package sit.int221.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.dtos.request.NewStatusDTO;
import sit.int221.entities.Statuses;
import sit.int221.entities.Tasks;
import sit.int221.exceptions.StatusNotFoundException;
import sit.int221.exceptions.TaskNotFoundException;
import sit.int221.repositories.StatusesRepository;

import java.util.List;

@Service
public class StatusesService {
    @Autowired
    StatusesRepository statusesRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<Statuses> getAllStatusesList() {
        return statusesRepository.findAll();
    }

    public Statuses findStatusById(Integer statusID) {
        return statusesRepository.findById(statusID).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Status "+ statusID + " Doesn't Exist!!!"));
    }

    public Statuses findStatusByName(String statusName) {
        return statusesRepository.findByName(statusName);
    }

    public Statuses insertStatus(NewStatusDTO newStatusDTO){
        newStatusDTO.setName(newStatusDTO.getName().trim());
        if(findStatusByName(newStatusDTO.getName()) != null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Duplicate i sus");
        }
        if(newStatusDTO.getDescription() != null && !newStatusDTO.getDescription().isBlank()){newStatusDTO.setDescription(newStatusDTO.getDescription().trim());}
        else{newStatusDTO.setDescription(null);}
        Statuses statuses = modelMapper.map(newStatusDTO, Statuses.class);
        return statusesRepository.saveAndFlush(statuses);
    }

    public void removeStatus(Integer statusId) {
        findStatusById(statusId);
        statusesRepository.deleteById(statusId);
    }

    public Statuses updateStatus(Integer statusId, NewStatusDTO newStatus) {
        Statuses findStatus = findStatusById(statusId);
        if((findStatusByName(newStatus.getName()) != null) && !(findStatus.getName().equals(newStatus.getName()))){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Duplicate i sus");
        }
        findStatus.setName(newStatus.getName().trim());
        if(newStatus.getDescription() != null && !newStatus.getDescription().isBlank()){newStatus.setDescription(newStatus.getDescription().trim());}
        else{newStatus.setDescription(null);}
        findStatus.setColor(newStatus.getColor().trim());
        statusesRepository.save(findStatus);
        return findStatus;
    }
}
