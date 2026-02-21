package com.application.InventoryService.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/circuitbreakers")
public class CircuitBreakerController {

    private final CircuitBreakerRegistry cbRegistry;

    public CircuitBreakerController(CircuitBreakerRegistry cbRegistry) {
        this.cbRegistry = cbRegistry;
    }

    @PostMapping("/{name}/state/{target}")
    public ResponseEntity<String> setState(@PathVariable String name,
                                           @PathVariable String target) {
        CircuitBreaker cb = cbRegistry.circuitBreaker(name); 
        switch (target.toLowerCase()) {
            case "open"       -> {cb.transitionToOpenState();
                return ResponseEntity.ok("CircuitBreaker '%s' transitioned to '%s'".formatted(name, target));}
            case "half_open"  -> {cb.transitionToHalfOpenState();
                return ResponseEntity.ok("CircuitBreaker '%s' transitioned to '%s'".formatted(name, target));}
            case "closed"     -> {cb.transitionToClosedState();
                return ResponseEntity.ok("CircuitBreaker '%s' transitioned to '%s'".formatted(name, target));}
            case "forced_open"-> {cb.transitionToForcedOpenState();
                return ResponseEntity.ok("CircuitBreaker '%s' transitioned to '%s'".formatted(name, target));}
            case "disabled"   -> {cb.transitionToDisabledState();
                return ResponseEntity.ok("CircuitBreaker '%s' transitioned to '%s'".formatted(name, target));
            }
            default -> {
                return ResponseEntity.badRequest()
                        .body("Unsupported state. Use one of: closed | open | half_open | forced_open | disabled");
            }
        }
        
    }
}
