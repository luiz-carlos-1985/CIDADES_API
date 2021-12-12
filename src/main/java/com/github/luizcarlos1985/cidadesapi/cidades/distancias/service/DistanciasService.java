package com.github.luizcarlos1985.cidadesapi.cidades.distancias.service;

import java.util.Arrays;

import com.github.luizcarlos1985.cidadesapi.cidades.Cidade;
import com.github.luizcarlos1985.cidadesapi.cidades.CidadeRepository;
import java.util.List;

import com.github.luizcarlos1985.cidadesapi.cidades.distancias.StringLocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import static java.lang.Math.*;

@Service
public class DistanciasService {

    Logger log = LoggerFactory.getLogger(DistanciasService.class);
    private final CidadeRepository cidadeRepository;


    public DistanciasService(final CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public Double distanceByPointsInMiles(final Long city1, final Long city2) {
        log.info("nativePostgresInMiles({}, {})", city1, city2);
        return cidadeRepository.distanceByPoints(city1, city2);
    }

    public Double distanceByCubeInMeters(Long city1, Long city2) {
        log.info("distanceByCubeInMeters({}, {})", city1, city2);
        final List<Cidade> cities = cidadeRepository.findAllById((Arrays.asList(city1, city2)));

        Point p1 = cities.get(0).getLocation();
        Point p2 = cities.get(1).getLocation();

        return cidadeRepository.distanceByCube(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public Double distanceUsingMath(final Long city1, final Long city2, final EarthRadius unit) {
        log.info("distanceUsingMath({}, {}, {})", city1, city2, unit);
        final List<Cidade> cities = cidadeRepository.findAllById((Arrays.asList(city1, city2)));

        final Double[] location1 = StringLocationUtils.transform(cities.get(0).getGeolocation());
        final Double[] location2 = StringLocationUtils.transform(cities.get(1).getGeolocation());

        return doCalculation(location1[0], location1[1], location2[0], location2[1], unit);
    }

    private double doCalculation(final double lat1, final double lon1, final double lat2,
                                 final double lng2, final EarthRadius earthRadius) {
        double lat = toRadians(lat2 - lat1);
        double lon = toRadians(lng2 - lon1);
        double a = sin(lat / 2) * sin(lat / 2) +
                cos(toRadians(lat1)) * cos(toRadians(lat2)) * sin(lon / 2) * sin(lon / 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));

        return earthRadius.getValue() * c;
    }
}

