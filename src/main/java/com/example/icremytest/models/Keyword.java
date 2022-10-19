package com.example.icremytest.models;

import com.example.icremytest.utils.Utils;

/**
 * @author Okala Bashir .O.
 */

public class Keyword {

    private String label;
    private String position;
    private PositionLabel positionLabel;
    private int volume;
    private int visits;

    public Keyword(String label, String position, int volume) {
        this.label = label;
        this.position = position;
        this.volume = volume;
    }

    public String getLabel() {
        return label;
    }

    public String getPosition() {
        return position;
    }

    public int getVolume() {
        return volume;
    }

    public int getVisits() {
        visits = switch (position) {
            case "1" -> (int)(0.27 * volume); //TODO Use Math.round()
            case "2" -> (int)(0.20 * volume);
            case "3" -> (int)(0.15 * volume);
            case "4" -> (int)(0.12 * volume);
            case "5" -> (int)(0.08 * volume);
            case "6" -> (int)(0.05 * volume);
            case "7" -> (int)(0.04 * volume);
            case "8", "9", "10" -> (int) 0.03 * volume;
            default -> 0;
        };
        return visits;
    }

    public PositionLabel getPositionLabel() {
        int position = Integer.parseInt(this.position);
        if (position > 100 || position < 1) {
            positionLabel = PositionLabel.NOT_PRESENT;
        }
        if (Utils.isBetween(position, 1, 10)) {
            this.positionLabel = PositionLabel.FIRST_PAGE;
        }

        if (Utils.isBetween(position, 11, 20)) {
            this.positionLabel = PositionLabel.SECOND_PAGE;
        }

        if (Utils.isBetween(position, 21, 99)) {
            this.positionLabel = PositionLabel.AFTER_SECOND_PAGE;
        } else if (this.position == "100"){
            this.positionLabel = PositionLabel.NOT_PRESENT;
        }

        return positionLabel;
    }

    //TODO remove this
    @Override
    public String toString() {
        return "Keyword{" +
                "label='" + label + '\'' +
                ", position='" + position + '\'' +
                ", positionLabel=" + getPositionLabel() +
                ", volume=" + volume +
                ", visits=" + getVisits() +
                '}';
    }
}
