package buildings;

import java.util.Iterator;


public class FloorIterator implements Iterator<Floor> {
        private Building building;
        private int index = 0;

        public FloorIterator(Building building){
            this.building = building;
        }

        @Override
        public boolean hasNext(){
            return index != building.getCountFloors()-1;
        }

        @Override
        public Floor next(){
            return building.getFloor(++index);
        }
}


