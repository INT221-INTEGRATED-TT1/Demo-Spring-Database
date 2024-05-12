package sit.int221.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.entities.Tasks;
import sit.int221.repositories.TasksRepository;


import java.util.List;

@Service
public class TasksService {
    @Autowired
    TasksRepository tasksRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<Tasks> getAllTasksList() {
        return tasksRepository.findAll();
    }

    public Tasks findTaskById(Integer taskId) {
        return tasksRepository.findById(taskId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Task "+ taskId + " Doesn't Exist!!!"));
    }

}
