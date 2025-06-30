import {Grid} from "@mui/material";
import AccommodationCard from "../AccommodationCard/AccommodationCard.jsx";
import React from 'react';
const AccommodationGrid = ({accommodations, onEdit, onDelete,onToggleRented}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {accommodations.map((acc) => (
                <Grid key={acc.id} size={{xs: 12, sm: 6, md: 4, lg: 3}}>
                    <AccommodationCard accommodation={acc} onEdit={onEdit} onDelete={onDelete} onToggleRented={onToggleRented}/>
                </Grid>
            ))}
        </Grid>
    )
}
export default AccommodationGrid;