create table BENCHMARKS (BID MEDIUMINT(11) NOT NULL AUTO_INCREMENT, NAME VARCHAR(32) NOT NULL, DATE DATE NOT NULL, PRIMARY KEY(BID,NAME));

create table PROBLEMS (PID MEDIUMINT(11) NOT NULL  AUTO_INCREMENT,NAME VARCHAR(256) NOT NULL,
RESOLUTION ENUM('SAT','MIN','MAX') NOT NULL, OBJECTIVE BIGINT(20) NULL, OPTIMAL TINYINT(1) NOT NULL,
PRIMARY KEY(PID, NAME));

create table RESOLUTIONS (BID MEDIUMINT(11) NOT NULL, PID MEDIUMINT(11) NOT NULL,
SOLVING_TIME BIGINT(20) NOT NULL, OBJECTIVE BIGINT(20) NULL, NB_SOL BIGINT(20) NOT NULL, NB_NODES BIGINT(20) NOT NULL,
NB_FAILS BIGINT(20) NOT NULL,
FOREIGN KEY (BID) references BENCHMARKS(BID), FOREIGN KEY (PID) references PROBLEMS(PID), PRIMARY KEY (BID, PID));
