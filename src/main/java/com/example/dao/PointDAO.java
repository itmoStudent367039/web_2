package com.example.dao;

import com.example.models.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PointDAO {
    private final List<Point> pointList = new ArrayList<>();

    public boolean addPoint(Point point) {
        return pointList.add(point);
    }
}
