package com.example.demo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRentalLog is a Querydsl query type for RentalLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalLog extends EntityPathBase<RentalLog> {

    private static final long serialVersionUID = 663935631L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalLog rentalLog = new QRentalLog("rentalLog");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath logMessage = createString("logMessage");

    public final StringPath logType = createString("logType");

    public final QReservation reservation;

    public QRentalLog(String variable) {
        this(RentalLog.class, forVariable(variable), INITS);
    }

    public QRentalLog(Path<? extends RentalLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRentalLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRentalLog(PathMetadata metadata, PathInits inits) {
        this(RentalLog.class, metadata, inits);
    }

    public QRentalLog(Class<? extends RentalLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservation = inits.isInitialized("reservation") ? new QReservation(forProperty("reservation"), inits.get("reservation")) : null;
    }

}

