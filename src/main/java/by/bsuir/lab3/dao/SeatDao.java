package by.bsuir.lab3.dao;

import by.bsuir.lab3.entity.Seat;

public interface SeatDao extends BaseDao<Seat> {

	Seat read(int row, int number);

}
