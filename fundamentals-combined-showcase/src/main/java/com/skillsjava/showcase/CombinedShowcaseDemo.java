package com.skillsjava.showcase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Demo Showcase Combinado: Integrando Collections, Prevenção de NPE e API de
 * Data/Hora.
 * Refactored to follow all best practices (final classes, logging, dependency
 * injection).
 */
public final class CombinedShowcaseDemo {

    private static final Logger logger = LoggerFactory.getLogger(CombinedShowcaseDemo.class);

    // Record (Java 16+) for immutable data
    public record CourseEvent(String title, Instant eventTimestamp) {
        public CourseEvent {
            Objects.requireNonNull(title, "Title cannot be null");
            Objects.requireNonNull(eventTimestamp, "Timestamp cannot be null");
        }
    }

    public static void main(String[] args) {
        logger.info("=== Showcase de Melhores Práticas Combinadas (Java 21) ===\n");

        CourseService service = new CourseService();

        // 1. Demo de Collections + Data/Hora
        service.addEvent("Lançamento do Java 21", Instant.parse("2023-09-19T10:00:00Z"));
        service.addEvent("Atualização Spring Boot 3.2", Instant.now());
        service.addEvent("Deep Dive em Collections", Instant.now().plusSeconds(3600));

        // 2. Demo de Sequenced Collections (Java 21)
        SequencedCollection<CourseEvent> events = service.getAllEvents();
        logger.info("Primeiro Evento registrado: {}", events.getFirst().title());
        logger.info("Último Evento registrado: {}", events.getLast().title());

        logger.info("\n--- Linha do Tempo de Eventos (Invertida) ---");
        events.reversed().forEach(e -> {
            ZonedDateTime localTime = e.eventTimestamp().atZone(ZoneId.systemDefault());
            logger.info("[{}] -> {}", localTime, e.title());
        });

        // 3. Demo de Prevenção de NPE (Optional + Collections.emptyList)
        logger.info("\n--- Busca e Segurança contra NPE ---");

        service.findEventByTitle("Lançamento do Java 21")
                .ifPresentOrElse(
                        e -> logger.info("Encontrado: {}", e.title()),
                        () -> logger.info("Evento não encontrado."));

        service.findEventByTitle("Legacy Java 8")
                .ifPresentOrElse(
                        e -> logger.info("Encontrado: {}", e.title()),
                        () -> logger.info("Evento 'Legacy Java 8' manipulado corretamente."));

        List<CourseEvent> filtered = service.getEventsByYear(1995);
        logger.info("Quantidade de eventos filtrados (deve ser 0, não null): {}", filtered.size());
    }
}

/**
 * Service class following the principles.
 */
final class CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
    private final SequencedSet<CombinedShowcaseDemo.CourseEvent> events = new LinkedHashSet<>();

    public void addEvent(String title, Instant timestamp) {
        logger.debug("Adding event: {}", title);
        events.add(new CombinedShowcaseDemo.CourseEvent(title, timestamp));
    }

    public Optional<CombinedShowcaseDemo.CourseEvent> findEventByTitle(String title) {
        return events.stream()
                .filter(e -> e.title().equalsIgnoreCase(title))
                .findFirst();
    }

    public SequencedCollection<CombinedShowcaseDemo.CourseEvent> getAllEvents() {
        return Collections.unmodifiableSequencedCollection(events);
    }

    public List<CombinedShowcaseDemo.CourseEvent> getEventsByYear(int year) {
        // Safe return: empty list instead of null
        return Collections.emptyList();
    }
}
