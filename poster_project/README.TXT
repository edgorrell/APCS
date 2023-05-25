Poster Methods:

1. Transparent Background: The method starts by drawing a white background on a blank image. Then it sets the alpha value to 20% and draws the stretched among us image, resulting in faded background. The alpha value is set back to 100% and the pipe is drawn on top

2. Full Mirror: Creates a subimage of 1/4 the original. Flips and copies the image for each quadrent. Draws an image of a skull emoji in the center of the pipes.

3. Recursion: The method starts with the original image and copies it 24 times, with each time the image being shrunk, rotated, and tinted. The tint is done by going trough each pixel of the image, checking if the value of the pixel is within the specified range, and then combines the tint color with the original color.

4. HSV Filter: This method goes over every pixel in the image. If the value of the pixel is >= 0.95, detecting the white background, it skips the pixel. Otherwise it generates a HSV color based on the x-value and the value of the pixel.

5. Invert Colors: Goes thorugh each pixel in the image, sets the pixel color to 255 minus the rgb values.

6. Pixelation: All this method does is scale down the image 16x to remove detail and then scales it back up to full size.
