package com.HealthyPetsBackend.controller;

import com.HealthyPetsBackend.model.Schedule;
import com.HealthyPetsBackend.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController @RequestMapping("/api/vets/{vetId}/slots")
public class SchedulesController {
  private final ScheduleService service;
  public SchedulesController(ScheduleService s){ this.service = s; }

  @GetMapping
  public List<Schedule.Slot> list(@PathVariable String vetId,
                                  @RequestParam(required=false) Instant from,
                                  @RequestParam(required=false) Instant to){
    return service.listSlots(vetId, from, to);
  }

  @PostMapping
  public Schedule add(@PathVariable String vetId, @RequestBody Schedule.Slot slot){
    return service.addSlot(vetId, slot);
  }

  // Para simplificar: borrado por índice dentro del array
  @DeleteMapping("/{index}")
  public Schedule remove(@PathVariable String vetId, @PathVariable int index){
    return service.removeSlot(vetId, index);
  }
}
