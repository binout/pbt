mkdir -p output

echo "Generate slides"
docker run --rm -v $(pwd):/slides amouat/decktape deck https://binout.github.io/pbt/index.html  /slides/output/bdxio-pbt.pdf
