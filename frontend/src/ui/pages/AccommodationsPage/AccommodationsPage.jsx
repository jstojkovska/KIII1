import useAccommodations from "../../../hooks/useAccommodations.js";
import {useState} from "react";
import {Box, Button, CircularProgress} from "@mui/material";
import AddAccommodationDialog from "../../components/accommodations/AddAccommodationDialog/AddAccommodationDialog.jsx";
import AccommodationGrid from "../../components/accommodations/AccommodationGrid/AccommodationGrid.jsx";

const AccommodationsPage = () => {
    const {accommodations, loading, onAdd, onEdit, onDelete,onToggleRented} = useAccommodations()
    const [addAccommodationDialogOpen, setAddAccommodationDialogOpen] = useState(false)

    return (
        <>
            <Box className="accommodations-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button variant="contained" color="primary"
                                    onClick={() => setAddAccommodationDialogOpen(true)}>
                                Add Accommodation
                            </Button>
                        </Box>
                        <AccommodationGrid accommodations={accommodations} onEdit={onEdit} onDelete={onDelete} onToggleRented={onToggleRented}/>
                    </>}
            </Box>
            <AddAccommodationDialog
                open={addAccommodationDialogOpen}
                onClose={() => setAddAccommodationDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    )
}

export default AccommodationsPage;