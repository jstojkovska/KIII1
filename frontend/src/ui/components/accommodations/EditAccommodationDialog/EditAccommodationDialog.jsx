import {useState} from "react";
import useCategoryType from "../../../../hooks/useCategoryType.js";
import useHosts from "../../../../hooks/useHosts.js";
import {
    Button, Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField
} from "@mui/material";

const EditAccommodationDialog = ({open, onClose, onEdit, accommodation}) => {
    const [formData, setFormData] = useState({
        "name": accommodation.name,
        "categoryType": accommodation.categoryType,
        "host": accommodation.host,
        "numRooms": accommodation.numRooms
    })
    const {categories} = useCategoryType();
    const {hosts} = useHosts();

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value})
    }

    const handleSubmit=()=>{
        onEdit(accommodation.id, formData)
        setFormData(formData)
        onClose();
    }

    return(
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Accommodation</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Number of Rooms"
                    name="numRooms"
                    type="number"
                    value={formData.numRooms}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Host</InputLabel>
                    <Select
                        name="host"
                        value={formData.host}
                        onChange={handleChange}
                        label="Host"
                    >
                        {hosts.map((host) => (
                            <MenuItem key={host.id} value={host.id}>
                                {host.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>

                <FormControl fullWidth margin="dense">
                    <InputLabel>Category Type</InputLabel>
                    <Select
                        name="categoryType"
                        value={formData.categoryType}
                        onChange={handleChange}
                        label="Category Type"
                    >
                        {categories && categories.map((category, index) => (
                            <MenuItem key={index} value={index}>
                                {category}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">
                    Edit
                </Button>
            </DialogActions>
        </Dialog>
    )

}

export default EditAccommodationDialog