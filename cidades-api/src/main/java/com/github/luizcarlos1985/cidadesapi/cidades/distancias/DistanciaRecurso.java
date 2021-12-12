package com.github.luizcarlos1985.cidadesapi.cidades.distancias;

        import com.github.luizcarlos1985.cidadesapi.cidades.distancias.service.DistanciasService;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distancias")
public class DistanciaRecurso {

    private final DistanciasService service;
    Logger log = LoggerFactory.getLogger(DistanciaRecurso.class);

    public DistanciaRecurso(DistanciasService service) {
        this.service = service;
    }

    @GetMapping("/points")
    public Double byPoints(@RequestParam(name = "from") final Long city1,
                           @RequestParam(name = "to") final Long city2) {
        log.info("byPoints");
        return service.distanceByPointsInMiles(city1, city2);
    }

    @GetMapping("/cube")
    public Double byCube(@RequestParam(name = "from") final Long city1,
                         @RequestParam(name = "to") final Long city2) {
        log.info("byCube");
        return service.distanceByCubeInMeters(city1, city2);
    }

}
