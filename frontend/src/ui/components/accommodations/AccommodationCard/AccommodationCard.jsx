import {useState} from "react";
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import DeleteAccommodationDialog from "../DeleteAccommodationDialog/DeleteAccommodationDialog.jsx";
import EditAccommodationDialog from "../EditAccommodationDialog/EditAccommodationDialog.jsx";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

const categoryTypeToString = (type) => {
    const validTypes = ["ROOM", "HOUSE", "FLAT", "APARTMENT", "HOTEL", "MOTEL"];
    return validTypes.includes(type) ? type : "UNKNOWN";
};



const AccommodationCard = ({accommodation, onEdit, onDelete, onToggleRented}) => {
    const [editAccommodationDialogOpen, setEditAccommodationDialogOpen] = useState(false)
    const [deleteAccommodationDialogOpen, setDeleteAccommodationDialogOpen] = useState(false)

    return (
        <>
            <Card sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography variant="h5">{accommodation.name}</Typography>
                    <Typography variant="subtitle2">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aperiam assumenda blanditiis cum
                        ducimus enim modi natus odit quibusdam veritatis.
                    </Typography>
                    <Typography variant="body1" fontWeight="bold"
                                sx={{textAlign: "center", fontSize: "1.25rem"}}>
                         {categoryTypeToString(accommodation.categoryType)}

                    </Typography>
                    <Typography variant="body2"
                                sx={{textAlign: "center", fontSize: "1.25rem"}}>
                        {accommodation.isRented ? "Rented" : "Available"}
                    </Typography>
                </CardContent>
                <CardActions sx={{justifyContent: "space-between"}}>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditAccommodationDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteAccommodationDialogOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                    <Box>
                        <Button
                            size="small"
                            variant="outlined"
                            color={accommodation.isRented ? "success" : "secondary"}
                            onClick={() => onToggleRented(accommodation.id)}
                        >
                            {accommodation.isRented ? "Mark as Available" : "Mark as Rented"}
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditAccommodationDialog
                open={editAccommodationDialogOpen}
                onClose={() => setEditAccommodationDialogOpen(false)}
                accommodation={accommodation}
                onEdit={onEdit}
            />
            <DeleteAccommodationDialog
                open={deleteAccommodationDialogOpen}
                onClose={() => setDeleteAccommodationDialogOpen(false)}
                accommodation={accommodation}
                onDelete={onDelete}
            />
        </>


    )
}

export default AccommodationCard;