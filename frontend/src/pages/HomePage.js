import '../App.css';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { Link } from "react-router-dom";
const HomePage = ()=>{
    return(<div className="Container">
        <div class="split left" >
        <div class="centered" style={{height:"50%", width:"50%"}}>
    <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column', borderRadius:'20%', border:'2px solid black'}}>
                  <CardMedia
                    component="div"
                    sx={{
                      // 16:9
                      pt: '56.25%',
                      objectFit: 'contain'
                    }}
                    image="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAMAAACahl6sAAAAhFBMVEX///8AAABPT08nJycHBwf8/Pz4+PgTExPx8fEEBAT19fULCwsYGBhCQkJzc3Pt7e0gICDl5eXOzs4yMjKdnZ04ODhjY2Pb29vAwMDGxsaPj4/e3t55eXm3t7eIiIhYWFiqqqpLS0uhoaFtbW1dXV0cHBzCwsIrKyuNjY2vr69/f39EREQUZkaBAAAJQUlEQVR4nO2caZuiuhKAZRMBRURExQWXdhzl//+/mxAEhFQFaM/1nufW+6V7jISqpLYsPaMRQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRD/70wOxzjV02vyZ/xtUX6BG820ElN/WN8WaBjjo629Y1y+LdMQNoHWZrX+tli9eUrUYHinbwvWDyuV68E4flu2PiB6/Ls08RE9NO30bfE6c0L10Lx/i8cvmmG3yfJLCWWR6IZn2vc0WnT6fqzQQ9OiTv1s97MpS6SBnnR7r6q7c00CPVQ/sFbqoU071CuXe8/34sx/GjLEruoR9YR0mJLd6v0BZ/87cwzb6dlQuOrE49/ab2NPNhX7UOc/l4r3btputpr/Qo+tVBh8mi+5KbBf3FP6Lk4QH9iwjvOxwUdjY0reu5wM1mMtDz831PfyXLgVv1thFM8Cz7GNzL+8ZE/4FxKsi538veeheozv0v40bYbZ6419wca6Xb+mDMJaAe9FtUcA6j68x3muKdovt5sAaY+g13rD3GQhM1SBDZvrH97+g3Zs8K/AAXh8A9/rD1JkD/aHFX55eXJFO84tZwc2X+DXekP83eJZdRNep29d3fz1g/0wwMci9cjlisChD/IQTreS4J1NMbLW9pmJKDLNnnk0SjE5oo4zAnawQPRQOJ8c7uqb1z8m63BdTiufEtDdc8OI0Z7vqGmBrs5xBtjWmT0nf2ynIe7MJxINriJAA10zmjWR4H4VPw9dxa9g42bKW8aswxX0WJ4lwFaO5bBveGCzIdPDH1vCV58dpa8xhfMaHyHoMZd5ZIKWUov75QpHi7HTVmPJHUpMSdpN+Do2qggctmzt7wi1ZNdi4SKDWtuLADvJC4lE6NRF9HdYbefIWyZoh0tFzFJ859BQw/SLdC7SGlr8yFmhzg4XcDHuIRzLhBPCezr0rq8C9VVIqvpuo2vHmTzYb7I9EmATzVOtgcKyPJY9XhEcq+IqjJ4/Kw+JdiA+C3XygshlsR6uUTApS2FNsNQ6vrSY5quXpkxa/+V7xJwWYq+dwDbLU8bIM5Khc0WCNJEaww9Wo0FskVCXYSs8XRVaJiYyoUyRPVitz4bMiGveoCbLw4LHRbWQRb/wF0t6xhBFmPrQ6/6gNQgbcFX1i0xZgiQ9niwHrK2O4NBc8XI61WxszyhEV5hHJNlutUEzsoPWo2PbQceFLRLhOMH1xNZHT0RYZnYKq5UzA2rNi6riWWlTWNQQX3fx7T3ohC5jbX/wV0s5yXO0Zah6O2BLq5VmYuZxhsuGOa8nT/ir5dylY5Oo12mZ5kBJkTnzHnuUV/GOXNNkYB3PR3badoadp8zco7WjBXI3Ck0twCKBqOKlqlrBwDp+xHNb1iwTxivFQjbnCOziLW619bOMUNSKslEQa2B4/YCxsJtSW6kWdKjbLJaD9XY9tbirdliKmlEyVpNiO2fYWcnG1H7q8ri65nU6qpgzO8ia47pmDnDGS+NzUTK24+VrLT/w0sHJEQtNwdbQzI7L/zUbwODdiiJWha/w6Zy8djftZnFYbq7gGxswD/Z2/cBnxX2w4bJRC6+z5r6ZVoNw4Cu1mcIsq9WI8W5Cp3Ipb/YvUtzcqNZcAMdYBryrWT5QrvLUijPPb9Tc949wvb1c8z2gWHHkZtW2UG51Ez5Wn/cMwItEvxXmaJ2WRRerh2iMtJsOHYzuLv65KKWsv+/nRLfi8VGs70/Sx2ubc4HmlOV8yIakOjLpsyd/yN7NcX15+s9Labb5UDvntpWFfj6kZcJc+JUqQfKaRysXavlsrZHq59pPnYmcRn/Cw5GZhJnUtqA7e0lYbiOfpO3luDXW9I/yuaqyGz/82T1Ynp+1JFreJ9DfM6tb374OrKiSfRYy5zHLQel2CcTaV1tkprREqdqdYxVMt8vy4y6b2IUqtWJ2V+sgH0T3cuZTFMTbkZWE7sjlmnhnXze6HPHOZ2/d6c2yOcze2s+Fvbhvh9JoWfi2c2VWQ7HehWkxRknkL0XBOl9U8aHw+CCyxuqcPG+dG2Y1t95Fs2bzMne9sLFj+5qSepR6/d44/5iV3Sel7dzZZ2FzMA6lJdzVOXkiPf+8ZbG/9+PZVNbIDfbRPMZ2ion0M24TbHw2z+VJfNS6cVPE2Xl9qm+ttDt+1jaFHWzhxrHOMlFReMlxaW88F5ElNyPP4/m6qM3H7SsINvf5XWNOs7ernPOk8ViMe8nf3noYk3rWrVEMaWVHhbXJjop5+bZu3U3w0pew4ar9BvS8dQef4wKYTISt9ClDuMTudUS7ElFhJ7tLkV9BkJyBFmHWWrab8OUZdoFPDisXFlLPKaVYiPDwuoujy7+8smRNpkiZieQBxgPQotP9pAaGVRXeLSkKfxfps4gzD6gnNr6L9mTli3fJ5znwBse1pxq5I8Cn4kWhIqK/KGcm4F0AJ5S6zwOzEyjtWqobfC1YznIBw+KIXTxxQHPKf0eucjG1J20Bbm7r5KcCSrubzgq8eGD3VVhYXVTCR8o3HKR3LXxJuC4B/B2TSUpgjVx0FvNkInyYu76LyJRP76IdZR0sAAG77Gel5A2eitN9sb4WUWuv9sFQbCX2Qb4vJT3gVrwZuzeiCeMSSSBuH3M2uSrHpYW8ngeiHEiguDfCyfLTYY1bGRyxCm7FZa8eyJdYskIDI+4ygkkxPjPgakadsLim0h35tYW+ikTQvZE6ntg61JaKa+acpNtV2xryg7O+aSTs5FbFV+wOvf+A1QiE/LKMtDRDmIjrMZ9jqQ4IDeSK9JxWT1yB+CB273JvKlWk52gEr63zz2H1DVvyyyJWv0RyH1LU4Ezyq2A9AHb2+01Jf4tWMh9Z/R6ANiF6xQzj8zPi5jeougNvOT6QorzJ9OM+YvYMH0tkE9hNugfhcc/xU3Iv7nF3w7wqzgTmj2ecLYOp7XkmhOfZ02D9qqM+RcrPgjz7FhgAgZDKs41ZfPnNn5O06Jl6FAy5Zv0hPhq28Hsh/zA9q1WUDqfe/xwfnBLvI3+jNxj8j1n7cPqqHkM2w6Q4X/T0gkvv7TAJxpDbS59m/uxRD8jViP5H/mcLa7Nf9d26eGFnx1//+epn2W0uydOPf9JUV5Om8dV/JqftN3MHQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRD/Zf4Dr1V8CHs43lUAAAAASUVORK5CYII="
                  />
                  <CardActions sx={{display:'flex', justifyContent:'center'}}>
                  <Link to="" style={{"textDecoration":"none"}}><Button size="large" variant="contained" onClick>User</Button></Link>
                  </CardActions>
                </Card>
  </div>
</div>

<div class="split right">
  <div class="centered" style={{height:"50%", width:"50%"}}>
  <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column', borderRadius:'20%', border:'2px solid black'}}>
                  <CardMedia
                    component="div"
                    sx={{
                      // 16:9
                      pt: '56.25%',
                      objectFit: 'contain'
                    }}
                    image="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXvRLjvJ-BNF3VEjaP-c9Q-fentb3KZ-t5qw&usqp=CAU"
                    />
                  <CardActions sx={{display:'flex', justifyContent:'center'}}>
                  <Link to="/Admin" style={{textDecoration:"none", cursor:"pointer"}}><Button size="large" variant="contained" >Admin</Button></Link>
                  </CardActions>
                </Card>
  </div>
</div>
    </div>)
}
export default HomePage;